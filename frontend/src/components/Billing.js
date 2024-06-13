import { useState, useEffect } from 'react';
const Billing = () => {
    const [billDetails, setBillDetails] = useState([]);
    useEffect(() => {
        fetch('http://localhost:8666/accountant/billing/billdetails')
            .then((res) => {
                return res.json();
            })
            .then((data) => {
                console.log(data);
                setBillDetails(data);
            });
    }, []);
    return (
        <div className="container"
             style={{backgroundColor: "#A9C8C5"}}>
            <h1>Billing Details</h1>

            {billDetails}
        </div>
    );
};
export default Billing;
