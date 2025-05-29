import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { login } from "../api/api";

export default function LoginPage() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");
    const navigate = useNavigate();

    const handleLogin = async () => {
        try {
            const success = await login(username, password);
            if (success) {
                navigate("/dashboard");
            }
        } catch (e) {
            setError("Невірний логін або пароль");
        }
    };

    return (
        <div className="flex h-screen justify-center items-center bg-gray-100">
            <div className="bg-white p-8 rounded-2xl shadow-xl w-80">
                <h2 className="text-2xl font-bold mb-6 text-center">Admin Login</h2>
                <input
                    className="w-full mb-4 px-4 py-2 border rounded-xl"
                    type="text"
                    placeholder="Логін"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                />
                <input
                    className="w-full mb-4 px-4 py-2 border rounded-xl"
                    type="password"
                    placeholder="Пароль"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                />
                {error && <p className="text-red-500 text-sm mb-4">{error}</p>}
                <button
                    className="w-full bg-blue-600 text-white py-2 rounded-xl hover:bg-blue-700"
                    onClick={handleLogin}
                >
                    Увійти
                </button>
            </div>
        </div>
    );
}
