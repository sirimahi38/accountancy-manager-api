import React ,{useState,useEffect} from "react";



































function Home() {
    const [data, setData] = useState(null);

    useEffect(() => {
        fetch('http://localhost:8666/accountant/tasks')

            .then(response => response.json())
            .then(json => setData(json))
            .catch(error => console.error(error));
    }, []);

    return (
        <>
            <h4>Api details</h4>
            {data ? <pre>{JSON.stringify(data, null, 2)}</pre> : 'Loading...'}
        </>
    );
}

export default Home;
 