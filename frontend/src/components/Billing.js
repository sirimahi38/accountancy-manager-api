
import React, { useState, useEffect } from "react";
function Billing() {





        const [task, setTask] = useState(null);
        useEffect(() => {
            fetch("http://localhost:8666/accountant/tasks", {
                method: "GET",
                headers: {
                    "Content-type": "application/json"
                    // "X-RapidAPI-Key": "your-api-key",
                    // "X-RapidAPI-Host": "jokes-by-api-ninjas.p.rapidapi.com",
                },
            })
                .then((response) => response.json())
                .then((data) => {
                    setTask(data[0].task);
                    console.log(data);
                })
                .catch((error) => console.log(error));
        }, []);
    return (
        <div className="container"
             style={{backgroundColor: "#A9C8C5"}}
        >
            <h1>Billing</h1>



                {task && <p>{task}</p>}

            </div>
        );
    }



export default Billing;