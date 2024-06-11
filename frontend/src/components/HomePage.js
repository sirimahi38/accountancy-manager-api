import React, { useState, useEffect } from "react";


function HomePage() {
    const [employees, setEmployees] = useState(null);
    useEffect(() => {
        fetch("http://localhost:8666/accountant/tasks/hello", {
            method: "GET",
            headers: {
                "Content-type": "application/json"

            },
        })
            .then((response) => response.json())
            .then((data) => {
                setEmployees(data);
                console.log(data);
            })
            .catch((error) => console.log(error));
    }, []);
    return (
        <div>
            <h2>Employee Details:</h2>
            {employees && <p>{employees}</p>}

        </div>
    );
}
export default HomePage;