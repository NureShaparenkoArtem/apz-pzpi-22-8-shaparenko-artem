import Sidebar from "../components/Sidebar";
import {useTranslation} from "react-i18next";

export default function Dashboard() {
    const { t } = useTranslation();
    return (
        <div className="flex">
            <Sidebar />
            <div className="p-6 w-full">
                <h1 className="text-2xl font-bold mb-4">{t("Dashboard.Title")}</h1>
                <p>{t("Dashboard.Hint")}</p>
            </div>
        </div>
    );
}
