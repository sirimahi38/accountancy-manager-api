
// import {Container,Nav, Navbar } from 'react-bootstrap';

import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';

import './Navbar.css';
function Navbars() {
    return (
        <div>
            <Navbar bg="dark" data-bs-theme="dark">
                <Container>
                    <Navbar.Brand href="#home">Account Management Application</Navbar.Brand>
                    <Nav className="me-auto">
                        <ul id="horizontal-list">
                            <li><a href="/HomePage">Admin</a></li>
                            <li><a href="/ListTasksComponent">Tasks</a></li>
                            <li><a href="/Billing">Billing</a></li>
                            <li><a href="/ContactUs">Contact</a></li>


                        </ul>
                    </Nav>
                </Container>
            </Navbar>
            <br/>



        </div>
    );
}

export default Navbars;