import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import LoginPage from "./pages/LoginPage";
import Dashboard from "./pages/Dashboard";
import Users from "./pages/Users";
import Stations from "./pages/Stations";
import Reservations from "./pages/Reservations";
import Backup from "./pages/Backup";

export default function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<LoginPage />} />
                <Route path="/dashboard" element={<Dashboard />} />
                <Route path="/users" element={<Users />} />
                <Route path="/stations" element={<Stations />} />
                <Route path="/reservations" element={<Reservations />} />
                <Route path="/backup" element={<Backup />} />
            </Routes>
        </Router>
    );
}
