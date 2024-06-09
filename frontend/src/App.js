
import './App.css';




import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Home from "./components/HomePage";
import Navbar from "./components/Navabar/Navbars";
import ListTasksComponent from "./components/ListTasksComponent";
import ContactUs from './components/ConactUs';
import Billing from "./components/Billing";
import HomePage from "./components/HomePage";
function App() {
    return (
        <>

<Navbar />

            <Router>

                <main className="main-content">
                    <Routes>
                        <Route path="/" element={<Home />} />
                        <Route path="/ListTasksComponent" element={<ListTasksComponent />} />
                        <Route path="/ContactUs" element={<ContactUs />} />
                        <Route path="/Billing" element={<Billing />} />
                        <Route path="/HomePage" element={<HomePage />} />
                    </Routes>
                </main>
            </Router>

            </>
    )
}

export default App;
