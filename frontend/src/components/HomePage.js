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
            <h2>Empoyees</h2>
            <table className='table table-stripped table-bordered'>
                <thead>
                <tr>
                    <th>Task Id</th>
                    <th>Task Group</th>
                    <th>Task Name </th>
                    <th>TaskCheckList</th>
                    <th>AdvanceIntimation</th>
                    <th>Remarks</th>
                    <th>TaskType</th>
                    <th>TaskAssignedTo</th>
                    <th>TaskStatus</th>
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