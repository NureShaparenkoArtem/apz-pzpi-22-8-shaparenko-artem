const API_BASE = "http://localhost:8080/api/v1";

const getAuthHeader = () => {
    const token = localStorage.getItem("token");
    return token ? { Authorization: `Bearer ${token}` } : {};
};

export const login = async (username, password) => {
    const response = await fetch(`${API_BASE}/auth/login`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({ username, password }),
    });

    if (!response.ok) {
        throw new Error("Login failed");
    }

    const data = await response.json();
    localStorage.setItem("token", data.token);
    return true;
};

export const fetchUsers = async () => {
    const response = await fetch(`${API_BASE}/user`, {
        headers: {
            ...getAuthHeader(),
        },
    });
    if (!response.ok) {
        throw new Error(`Failed to fetch users: ${response.status}`);
    }
    return response.json();
};

export const deleteUser = async (id) => {
    const response = await fetch(`${API_BASE}/user/${id}`, {
        method: "DELETE",
        headers: {
            ...getAuthHeader(),
        },
    });
    if (!response.ok) {
        throw new Error(`Failed to delete user: ${response.status}`);
    }
    return response;
};

export const fetchStations = async () => {
    const response = await fetch(`${API_BASE}/stations`, {
        headers: {
            ...getAuthHeader(),
        },
    });
    if (!response.ok) {
        throw new Error(`Failed to fetch stations: ${response.status}`);
    }
    return response.json();
};

export const deleteStation = async (id) => {
    const response = await fetch(`${API_BASE}/stations/${id}`, {
        method: "DELETE",
        headers: {
            ...getAuthHeader(),
        },
    });
    if (!response.ok) {
        throw new Error(`Failed to delete station: ${response.status}`);
    }
    return response;
};

export const fetchReservations = async () => {
    const response = await fetch(`${API_BASE}/reservations`, {
        headers: {
            ...getAuthHeader(),
        },
    });
    if (!response.ok) {
        throw new Error(`Failed to fetch reservations: ${response.status}`);
    }
    return response.json();
};

export const deleteReservation = async (id) => {
    const response = await fetch(`${API_BASE}/reservations/${id}`, {
        method: "DELETE",
        headers: {
            ...getAuthHeader(),
        },
    });
    if (!response.ok) {
        throw new Error(`Failed to delete reservation: ${response.status}`);
    }
    return response;
};

export const createBackup = async () => {
    const response = await fetch(`${API_BASE}/backup`, {
        method: "POST",
        headers: {
            ...getAuthHeader(),
        },
    });
    if (!response.ok) {
        throw new Error(`Failed to create backup: ${response.status}`);
    }
    return response.text();
};

export const updateUser = async (id, updatedData) => {
    const params = new URLSearchParams({
        email: updatedData.email,
        phone: updatedData.phone,
    });

    const response = await fetch(`${API_BASE}/user/updateUserByAdmin/${id}?${params.toString()}`, {
        method: "PUT",
        headers: {
            ...getAuthHeader(),
        },
    });

    if (!response.ok) {
        throw new Error(`Failed to update user: ${response.status}`);
    }

    return response.json();
};

export const updateStation = async (id, updatedData) => {
    const params = new URLSearchParams({
        station_name: updatedData.station_name,
        location: updatedData.location,
    });

    const response = await fetch(`${API_BASE}/stations/updateStationByAdmin/${id}?${params.toString()}`, {
        method: "PUT",
        headers: {
            ...getAuthHeader(),
        },
    });

    if (!response.ok) {
        throw new Error(`Failed to update station: ${response.status}`);
    }

    return response.json();
};
