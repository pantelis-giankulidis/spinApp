import { useState, useEffect } from "react"

const DisplayResults = ({ query }) => {
    const [results, setResults] = useState([]);

    useEffect(() => {
        fetch("http://localhost:8080/spinapp/query?" + query, {
            mode: 'no-cors'
        }).then((res) => {
            return res.body.json();
        })
            .then(
                (result) => {
                    setResults(result);
                },
                (error) => {
                    setResults(error.message);
                }
            )
    }, []);

    return (
        <>
            <div className="app">
                <h1>RESULTS</h1>
                {results}
                <h4>{query}</h4>
            </div>
        </>
    )
}

export default DisplayResults;