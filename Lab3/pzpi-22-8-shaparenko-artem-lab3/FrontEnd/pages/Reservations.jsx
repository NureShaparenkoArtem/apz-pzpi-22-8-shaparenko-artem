import { useEffect, useState } from "react";
import { fetchReservations, deleteReservation } from "../api/api";
import Sidebar from "../components/Sidebar";
import {useTranslation} from "react-i18next";

export default function Reservations() {
    const [reservations, setReservations] = useState([]);
    const [selectedReservation, setSelectedReservation] = useState(null);
    const { t } = useTranslation();

    useEffect(() => {
        fetchReservations().then(setReservations);
    }, []);

    const handleDelete = async (id) => {
        await deleteReservation(id);
        setReservations((prev) => prev.filter((r) => r.id !== id));
        setSelectedReservation(null);
    };

    const handleRowClick = (reservation) => {
        setSelectedReservation(reservation);
    };

    return (
        <div className="flex">
            <Sidebar />
            <div className="p-6 w-full">
                <h1 className="text-xl font-bold mb-4">{t("ReservationsPanel.Title")}</h1>
                <table className="w-2/3 border text-sm">
                    <thead>
                    <tr className="bg-gray-200 text-left">
                        <th className="p-2">ID</th>
                        <th>{t("ReservationsPanel.Table.StartTime")}</th>
                        <th>{t("ReservationsPanel.Table.EndTime")}</th>
                        <th>{t("ReservationsPanel.Table.Status")}</th>
                        <th>{t("ReservationsPanel.Table.MechanicNeed")}</th>
                        <th>{t("ReservationsPanel.Table.Station")}</th>
                        <th>{t("ReservationsPanel.Table.Car")}</th>
                    </tr>
                    </thead>
                    <tbody>
                    {reservations.map((res) => (
                        <tr
                            key={res.id}
                            className={`border-t cursor-pointer ${
                                selectedReservation?.id === res.id ? "bg-yellow-100" : ""
                            }`}
                            onClick={() => handleRowClick(res)}
                        >
                            <td className="p-2">{res.reservation_id}</td>
                            <td>{res.reservationTime}</td>
                            <td>{res.reservation_end_time}</td>
                            <td>{res.status}</td>
                            <td>{res.mechanic_need ? t("Yes") : t("No")}</td>
                            <td>{res.station.station_name}</td>
                            <td>{res.vehicle.make + " " + res.vehicle.model}</td>
                        </tr>
                    ))}
                    </tbody>
                </table>

                {selectedReservation && (
                    <div className="mt-4 p-4 bg-gray-100 rounded shadow-md max-w-md">
                        <h2 className="text-lg font-semibold mb-2">{t("ReservationsPanel.EditTitle")}</h2>
                        <p><strong>ID:</strong> {selectedReservation.reservation_id}</p>
                        <p><strong>{t("ReservationsPanel.Table.StartTime")}:</strong> {selectedReservation.reservationTime}</p>
                        <p><strong>{t("ReservationsPanel.Table.EndTime")}:</strong> {selectedReservation.reservation_end_time}</p>
                        <p><strong>{t("ReservationsPanel.Table.Status")}:</strong> {selectedReservation.status}</p>
                        <p><strong>{t("ReservationsPanel.Table.MechanicNeed")}:</strong> {selectedReservation.mechanic_need ? "Так" : "Ні"}</p>
                        <p><strong>{t("ReservationsPanel.Table.Station")}:</strong> {selectedReservation.station.station_name}</p>
                        <p><strong>{t("ReservationsPanel.Table.Car")}:</strong> {selectedReservation.vehicle.make + " " + selectedReservation.vehicle.model}</p>
                        <button
                            className="mt-4 bg-red-500 text-white px-4 py-2 rounded hover:bg-red-600"
                            onClick={() => handleDelete(selectedReservation.id)}
                        >
                            {t("DeleteButton")}
                        </button>
                        <button
                            className="mt-2 ml-2 bg-gray-400 text-white px-4 py-2 rounded hover:bg-gray-500"
                            onClick={() => setSelectedReservation(null)}
                        >
                            {t("CancelButton")}
                        </button>
                    </div>
                )}
            </div>
        </div>
    );
}
