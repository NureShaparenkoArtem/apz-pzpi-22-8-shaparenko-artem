import { useEffect, useState } from "react";
import { fetchUsers, deleteUser, updateUser } from "../api/api";
import Sidebar from "../components/Sidebar";
import {useTranslation} from "react-i18next";

export default function Users() {
    const [users, setUsers] = useState([]);
    const [selectedUser, setSelectedUser] = useState(null);
    const [editData, setEditData] = useState({ email: "", phone: "" });
    const { t } = useTranslation();

    useEffect(() => {
        fetchUsers().then(setUsers);
    }, []);

    const handleDelete = async (id) => {
        await deleteUser(id);
        setUsers(users.filter((u) => u.id !== id));
        setSelectedUser(null);
        fetchUsers().then(setUsers);
    };

    const handleRowClick = (user) => {
        setSelectedUser(user);
        setEditData({ email: user.email, phone: user.phone });
    };

    const handleSave = async () => {
        await updateUser(selectedUser.user_id, {
            email: editData.email,
            phone: editData.phone,
        });

        setSelectedUser(null);

        fetchUsers().then(setUsers);
    };

    return (
        <div className="flex">
            <Sidebar />
            <div className="p-6 w-full">
                <h1 className="text-xl font-bold mb-4">{t("UsersPanel.Title")}</h1>
                <table className="w-2/3 border text-sm">
                    <thead>
                    <tr className="bg-gray-200 text-left">
                        <th className="p-2">{t("UsersPanel.Table.Fullname")}</th>
                        <th>{t("UsersPanel.Table.Email")}</th>
                        <th>{t("UsersPanel.Table.PhoneNumber")}</th>
                    </tr>
                    </thead>
                    <tbody>
                    {users.map((u) => (
                        <tr
                            key={u.id}
                            className={`border-t cursor-pointer ${
                                selectedUser?.user_id === u.user_id ? "bg-yellow-100" : ""
                            }`}
                            onClick={() => handleRowClick(u)}
                        >
                            <td className="p-2">{u.first_name} {u.last_name}</td>
                            <td>{u.email}</td>
                            <td>{u.phone}</td>
                        </tr>
                    ))}
                    </tbody>
                </table>

                {selectedUser && (
                    <div className="mt-4 p-4 bg-gray-100 rounded shadow-md max-w-md">
                        <h2 className="text-lg font-semibold mb-2">{t("UsersPanel.EditTitle")}</h2>
                        <label className="block mb-2">
                            {t("UsersPanel.Table.Email")}:
                            <input
                                type="email"
                                value={editData.email}
                                onChange={(e) => setEditData({ ...editData, email: e.target.value })}
                                className="w-full p-2 border rounded"
                            />
                        </label>
                        <label className="block mb-2">
                            {t("UsersPanel.Table.PhoneNumber")}:
                            <input
                                type="text"
                                value={editData.phone}
                                onChange={(e) => setEditData({ ...editData, phone: e.target.value })}
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
                                onClick={() => handleDelete(selectedUser.user_id)}
                            >
                                {t("DeleteButton")}
                            </button>
                            <button
                                className="bg-gray-400 text-white px-4 py-2 rounded hover:bg-gray-500"
                                onClick={() => setSelectedUser(null)}
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
