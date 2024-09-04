package com.ca.account.manager.security;

import org.springframework.core.task.TaskExecutor;
import org.springframework.security.concurrent.DelegatingSecurityContextCallable;
import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService;
import org.springframework.security.concurrent.DelegatingSecurityContextRunnable;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.task.DelegatingSecurityContextTaskExecutor;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

import static java.util.Collections.emptyList;

/**
 * Thin utility layer around Spring Security's authentication framework.
 */
public final class SecurityContextHolderUtils {

	private static final String SYSTEM_USER= "SUPER_USER";


	private SecurityContextHolderUtils() {
		// non-instantiatable
	}

	public static String authenticatedId() {
		return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
				.filter(Authentication::isAuthenticated)
				.map(Authentication::getName)
				.orElse(null);
	}

	public static Runnable runnableUnderId(String authenticatedId, Runnable runnable) {
		return DelegatingSecurityContextRunnable.create(
				runnable, securityContextWithAuthenticatedPrincipal(authenticatedId));
	}

	public static SecurityContext securityContextWithAuthenticatedPrincipal(String id) {
		return securityContextWith(preAuthenticated(() -> id));
	}

	public static Runnable runningAs(String authenticatedId, Runnable runnable) {
		return DelegatingSecurityContextRunnable.create(
				runnable, securityContextWithAuthenticatedPrincipal(authenticatedId));
	}

	public static void runAs(String authenticatedId, Runnable runnable) {
		runnableUnderId(authenticatedId, runnable).run();
	}

	public static Executor systemUserExecutor(TaskExecutor delegate) {
		return new DelegatingSecurityContextTaskExecutor(
				delegate,
				securityContextWithAuthenticatedPrincipal(SYSTEM_USER)
		);
	}

	public static ExecutorService systemUserExecutorService(ExecutorService delegate) {
		return new DelegatingSecurityContextExecutorService(
				delegate,
				securityContextWithAuthenticatedPrincipal(SYSTEM_USER)
		);
	}

	public static void runAsSystemUser(Runnable runnable) {
		runAs(SYSTEM_USER, runnable);
	}

	public static <T> T callAsSystemUser(Callable<T> callable) {
		return callAs(SYSTEM_USER, callable);
	}

	private static SecurityContext securityContextWith(Authentication authentication) {
		SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
		securityContext.setAuthentication(authentication);
		return securityContext;
	}

	private static Authentication preAuthenticated(AuthenticatedPrincipal principal) {
		return new PreAuthenticatedAuthenticationToken(principal, null, emptyList());
	}

	private static <T> T callAs(String userId, Callable<T> callable) {
		try {
			return callableUnderId(userId, callable).call();
		} catch (Exception problemDuringCall) {
			throw new RuntimeException(problemDuringCall);
		}
	}

	private static <T> Callable<T> callableUnderId(String authenticatedId, Callable<T> callable) {
		return DelegatingSecurityContextCallable.create(
				callable, securityContextWithAuthenticatedPrincipal(authenticatedId));
	}
}
