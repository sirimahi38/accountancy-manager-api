import React, { useEffect, useState } from 'react';


const Home = () => {

    const [tasks, setTasks] = useState([]);
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        setLoading(true);

        fetch('api/getTaskDetails')
            .then(response => response.json())
            .then(data => {
                setTasks(data);
                setLoading(false);
            })
    }, []);

    if (loading) {
        return <p>Loading...</p>;
    }

    return (
        <div className="App">
            <header className="App-header">
                <img src={logo} className="App-logo" alt="logo" />
                <div className="App-intro">
                    <h2>Tasks List</h2>
                    {tasks.map(group =>
                        <div key={task.id}>
                            {task.name}
                        </div>
                    )}
                </div>
            </header>
        </div>
    );
}

export default Home;