import { Link } from "react-router-dom";
import { useTranslation } from "react-i18next";

export default function Sidebar() {
    const { t, i18n } = useTranslation();

    const toggleLanguage = () => {
        const newLang = i18n.language === "en" ? "uk" : "en";
        i18n.changeLanguage(newLang);
    };

    return (
        <div className="w-60 bg-gray-900 text-white min-h-screen p-4 flex flex-col justify-between">
            <div>
                <h2 className="text-xl font-bold mb-6">{t("Sidebar.Title")}</h2>
                <nav className="flex flex-col space-y-3">
                    <Link to="/dashboard" className="hover:text-blue-400">{t("Sidebar.HomeButton")}</Link>
                    <Link to="/users" className="hover:text-blue-400">{t("Sidebar.UsersButton")}</Link>
                    <Link to="/stations" className="hover:text-blue-400">{t("Sidebar.StationsButton")}</Link>
                    <Link to="/reservations" className="hover:text-blue-400">{t("Sidebar.ReservationsButton")}</Link>
                    <Link to="/backup" className="hover:text-blue-400">{t("Sidebar.BackupButton")}</Link>
                </nav>
            </div>
            <div className="mt-6">
                <button
                    onClick={toggleLanguage}
                    className="text-sm text-gray-400 hover:text-white underline"
                >
                    {i18n.language === "en" ? "Переключити на українську" : "Switch to English"}
                </button>
            </div>
        </div>
    );
}
