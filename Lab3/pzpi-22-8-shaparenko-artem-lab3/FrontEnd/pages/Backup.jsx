import { useState } from "react";
import { createBackup } from "../api/api";
import Sidebar from "../components/Sidebar";
import {useTranslation} from "react-i18next";

export default function Backup() {
    const [status, setStatus] = useState("");
    const { t } = useTranslation();

    const handleBackup = () => {
        setStatus("Створення...");
        createBackup()
            .then((text) => setStatus(text))
            .catch(() => setStatus("Помилка при створенні."));
    };

    return (
        <div className="flex">
            <Sidebar />
            <div className="p-6 w-full">
                <h1 className="text-xl font-bold mb-4">{t("BackupPanel.Title")}</h1>
                <button
                    className="bg-blue-600 text-white px-4 py-2 rounded-xl hover:bg-blue-700"
                    onClick={handleBackup}
                >
                    {t("BackupPanel.BackupButton")}
                </button>
                {status && <p className="mt-4 text-gray-800">{status}</p>}
            </div>
        </div>
    );
}
