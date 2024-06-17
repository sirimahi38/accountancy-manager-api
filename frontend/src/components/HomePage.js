import React, {useEffect, useState} from "react";

function HomePage(){
    const [taskDetails, setTaskDetails] = useState([]);
    useEffect(() => {
        fetch('http://localhost:8666/accountant/billing/billdetails/')
            .then((res) => {
                return res.json();
            })
            .then((data) => {
                console.log("data is loading ");
                setTaskDetails(data);

            });
    }, []);

    return (
        <div className="employee-tasks"
             style={{backgroundColor: "#09BCA9 "}}
        >
            <h2>Admin User Details</h2>
            <table className='table table-stripped table-bordered'>
                <thead>
                <tr>
                    <th>User ID</th>
                    <th>User Name</th>
                    <th>User Role </th>
                    <th>User Access status</th>
                    <th>User Assign</th>
                    <th>User Type</th>

                    <th>UserAssignedTo</th>
                    <th>UserStatus</th>
                </tr>
                </thead>
                <tbody>
                {taskDetails.map(employee => (
                    <tr key={employee.taskId}>
                        <td>{employee.taskId}</td>
                        <td>{employee.taskGroup}</td>
                        <td>{employee.taskName}</td>
                        <td>{employee.taskCheckList}</td>
                        <td>{employee.advanceIntimation}</td>
                        <td>{employee.remarks}</td>
                        <td>{employee.taskType}</td>
                        <td>{employee.taskAssignedTo}</td>
                        <td>{employee.taskStatus}</td>
                    </tr>))
                }

                </tbody>
            </table>

        </div>
    )
}

export default HomePage;