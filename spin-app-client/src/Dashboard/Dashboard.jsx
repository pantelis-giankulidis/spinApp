import { useState, useEffect } from "react"
import DisplayResults from "../DisplayResults/DisplayResults";

import Dropdown from 'react-bootstrap/Dropdown';
import ButtonGroup from 'react-bootstrap/ButtonGroup';
import DropdownButton from 'react-bootstrap/DropdownButton';
import './Dashboard.css';

const Dashboard = () => {

    const [errorstate, setErrorstate] = useState(false);
    const [submitted, setSubmitted] = useState(false);
    const [urlParams, setUrlParams] = useState("");

    const [lowerPrice, setLowerPrice] = useState();
    const [higherPrice, setHigherPrice] = useState("");
    const [lowerSize, setLowerSize] = useState("");
    const [higherSize, setHigherSize] = useState("");
    const [year, setYear] = useState("");

    const locations = [
        { value: "l1", label: "ATHENS" },
        { value: "l2", label: "THESSALONIKI" },
        { value: "l3", label: "PATRA" },
        { value: "l4", label: "CHANIA" }
    ];

    const availability = [
        { value: "a1", label: "SALE" },
        { value: "a2", label: "RENT" }
    ];

    const type = [
        { value: "t1", label: "STUDIO" },
        { value: "t2", label: "LOFT" },
        { value: "t3", label: "APARTMENT" },
        { value: "t4", label: "MAISONETTE" }
    ];

    const handleSubmit = (event) => {
        event.preventDefault();
        setSubmitted(true);

        let params = "";

        if (lowerPrice !== "" && higherPrice !== "") {
            params = params + "&priceRange=" + lowerPrice + "," + higherPrice;
        }

        if (lowerSize !== "" && higherSize !== "") {
            params = params + "&sizeRange=" + lowerSize + "," + higherSize;
        }
        if (year !== "") {
            params = params + "&year=" + year;
        }

        setUrlParams(params);
    };


    const renderErrorMessage = (event) => {

    }

    const handleLPChange = (event) => {
        setLowerPrice(event.target.value);
    }



    if (!submitted) {
        return (<>
            <div className="app">
                <div className="login-form">
                    <div className="title">Spin app Query in Properties</div>

                    <div className="input-container">
                        <form className="input-container" onSubmit={handleSubmit}>
                            <span className="upperLine">
                                <label className="upperLine">LOCATION</label>
                                <select>
                                    <option value="ATHENS">ΑΘΗΝΑ</option>
                                    <option value="THESSALONIKI">ΘΕΣ/ΝΙΚΗ</option>
                                    <option value="PATRA">ΠΑΤΡΑ</option>
                                    <option value="CHANIA">ΧΑΝΙΑ</option>
                                </select>
                                <label className="upperLine">ΤΥΠΟΣ ΚΤΙΡΙΟΥ</label>
                                <select>
                                    <option value="STUDIO">STUDIO</option>
                                    <option value="LOFT">LOFT</option>
                                    <option value="APARTMENT">APARTMENT</option>
                                    <option value="MAISONETTE">MAISONETTE</option>
                                </select>
                                <label className="upperLine">ΔΙΑΘΕΣΙΜΟΤΗΤΑ</label>
                                <select>
                                    <option value="SALE">ΠΩΛΗΣΗ</option>
                                    <option value="RENT">ΕΝΟΙΚΙΑΣΗ</option>
                                </select>
                            </span>

                            <span>
                                <label>ΤΙΜΗ ΑΠΟ:</label>
                                <input type="text" value={lowerPrice} onChange={(change) => handleLPChange(change.text)} />
                                <label>ΤΙΜΗ ΕΩΣ:</label>
                                <input type="text" value={higherPrice} onChange={(change) => setHigherPrice(change.text)} />
                            </span>
                            <div>
                                <span>
                                    <label>ΜΕΓΕΘΟΣ ΑΠΟ:</label>
                                    <input type="text" value={lowerSize} onChange={(change) => setLowerSize(change.text)} />
                                    <labal>ΜΕΓΕΘΟΣ ΕΩΣ</labal>
                                    <input type="text" value={higherSize} onChange={(change) => setHigherSize(change.text)} />
                                </span>
                            </div>

                            <div className="input-item">
                                <label>ΕΤΟΣ ΚΑΤΑΣΚΕΥΗΣ ΜΕΓΑΛΥΤΕΡΟ ΑΠΟ:</label>
                                <input type="text" value={year} onChange={(change) => setYear(change.text)} />
                            </div>
                            <input class="submitButton" type="submit" value="SEARCH" ></input>
                        </form>
                    </div>
                    <h5>{urlParams}</h5>
                </div>
            </div>
        </>);
    } else {
        return (
            <div>
                <DisplayResults query={urlParams}></DisplayResults>
            </div>
        );
    }
}

export default Dashboard;