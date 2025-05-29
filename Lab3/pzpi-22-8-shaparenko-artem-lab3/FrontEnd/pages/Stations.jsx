import { useEffect, useState } from "react";
import {fetchStations, deleteStation, updateStation} from "../api/api";
import Sidebar from "../components/Sidebar";
import {useTranslation} from "react-i18next";

export default function Stations() {
    const [stations, setStations] = useState([]);
    const [selectedStation, setSelectedStation] = useState(null);
    const [editData, setEditData] = useState({ station_name: "", location: "" });
    const { t } = useTranslation();

    useEffect(() => {
        fetchStations().then(setStations);
    }, []);

    const handleDelete = async (id) => {
        await deleteStation(id);
        setStations(stations.filter((s) => s.station_id !== id));
        setSelectedStation(null);
    };

    const handleRowClick = (station) => {
        setSelectedStation(station);
        setEditData({ station_name: station.station_name, location: station.location });
    };

    const handleSave = async () => {
        await updateStation(selectedStation.station_id, {
            station_name: editData.station_name,
            location: editData.location,
        });

        setSelectedStation(null);
        fetchStations().then(setStations);
    };

    return (
        <div className="flex">
            <Sidebar />
            <div className="p-6 w-full">
                <h1 className="text-xl font-bold mb-4">{t("StationsPanel.Title")}</h1>
                <table className="w-2/3 border text-sm">
                    <thead>
                    <tr className="bg-gray-200 text-left">
                        <th className="p-2">{t("StationsPanel.Table.Name")}</th>
                        <th>{t("StationsPanel.Table.Address")}</th>
                        <th>{t("StationsPanel.Table.Price")}</th>
                        <th>{t("StationsPanel.Table.Type")}</th>
                    </tr>
                    </thead>
                    <tbody>
                    {stations.map((station) => (
                        <tr
                            key={station.station_id}
                            className={`border-t cursor-pointer ${
                                selectedStation?.station_id === station.station_id ? "bg-yellow-100" : ""
                            }`}
                            onClick={() => handleRowClick(station)}
                        >
                            <td className="p-2">{station.station_name}</td>
                            <td>{station.location}</td>
                            <td>{station.hourly_price} ₴</td>
                            <td>{station.station_type}</td>
                        </tr>
                    ))}
                    </tbody>
                </table>

                {selectedStation && (
                    <div className="mt-4 p-4 bg-gray-100 rounded shadow-md max-w-md">
                        <h2 className="text-lg font-semibold mb-2">{t("StationsPanel.EditTitle")}</h2>
                        <label className="block mb-2">
                            {t("StationsPanel.Table.Name")}:
                            <input
                                type="text"
                                value={editData.station_name}
                                onChange={(e) => setEditData({ ...editData, station_name: e.target.value })}
                                className="w-full p-2 border rounded"
                            />
                        </label>
                        <label className="block mb-2">
                            {t("StationsPanel.Table.Address")}:
                            <input
                                type="text"
                                value={editData.location}
                                onChange={(e) => setEditData({ ...editData, location: e.target.value })}
                                className="w-full p-2 border rounded"
                            />
                        </label>
                        <div className="flex gap-2 mt-4">
                            <button
                                className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600"
                                onClick={handleSave}
                            >
                                {t("SaveButton")}
                            </button>
                            <button
                                className="bg-red-500 text-white px-4 py-2 rounded hover:bg-red-600"
                                onClick={() => handleDelete(selectedStation.station_id)}
                            >
                                {t("DeleteButton")}
                            </button>
                            <button
                                className="bg-gray-400 text-white px-4 py-2 rounded hover:bg-gray-500"
                                onClick={() => setSelectedStation(null)}
                            >
                                {t("CancelButton")}
                            </button>
                        </div>
                    </div>
                )}
            </div>
        </div>
    );
}
