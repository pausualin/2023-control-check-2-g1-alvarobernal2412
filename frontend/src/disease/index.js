import { useState, useEffect } from "react";
import { Table } from "reactstrap";
import "bootstrap/dist/css/bootstrap.min.css"; // Asegúrate de importar Bootstrap

export default function DiseasesListing() {
    const [diseases, setDiseases] = useState([]);

    useEffect(() => {
        fetch("/api/v1/diseases")
            .then((response) => {
                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }
                return response.json();
            })
            .then((data) => {
                if (Array.isArray(data)) {
                    setDiseases(data);
                } else {
                    console.error("Unexpected API response format:", data);
                }
            })
            .catch((error) => console.error("Error fetching diseases:", error));
    }, []);

    return (
        <div>
            <h1>Diseases List</h1>
            <Table>
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Susceptible Pet Types</th>
                    </tr>
                </thead>
                <tbody>
                    {diseases.length > 0 ? (
                        diseases.map((disease, index) => (
                            <tr key={index}>
                                <td>{disease.name}</td>
                                <td>
                                    <ul>
                                        {disease.susceptiblePetTypes.map((petType, idx) => (
                                            <li key={idx}>{petType.name}</li>
                                        ))}
                                    </ul>
                                </td>
                            </tr>
                        ))
                    ) : (
                        <tr>
                            <td colSpan="2">No diseases found</td>
                        </tr>
                    )}
                </tbody>
            </Table>
        </div>
    );
}
