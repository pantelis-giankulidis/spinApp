import { useState, useEffect } from "react"
import DisplayResults from "../DisplayResults/DisplayResults";
import Dropdown from "../Dropdown/Dropdown";
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
                            <span>
                                <label>LOCATION</label>
                                <Dropdown placeHolder="Location" options={locations}></Dropdown>
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
                            <span>
                                <label>ΔΙΑΘΕΣΙΜΟΤΗΤΑ</label>
                                <Dropdown placeHolder="Availability" options={availability}></Dropdown>
                            </span>
                            <span>
                                <label>ΤΥΠΟΣ ΚΤΙΡΙΟΥ</label>
                                <Dropdown placeHolder="Type" options={type}></Dropdown>
                            </span>
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