
import './App.css';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Navbar from "./components/Navabar/Navbars";
import ListTasksComponent from "./components/ListTasksComponent";
import ContactUs from './components/ConactUs';
import Billing from "./components/Billing";
import HomePage from "./components/HomePage";



function App() {
    return (
        <>

        <header>
            <div className="">
                <Navbar/>

                <Router>


                    <main className="main-content">
                        <Routes>
                            <Route path="/" element={<HomePage/>}/>
                            <Route path="/ListTasksComponent" element={<ListTasksComponent/>}/>
                            <Route path="/ContactUs" element={<ContactUs/>}/>
                            <Route path="/Billing" element={<Billing/>}/>
                            <Route path="/HomePage" element={<HomePage/>}/>

                        </Routes>
                    </main>



        </Router>
            </div>
        </header>

            </>
    )
}

export default App;
