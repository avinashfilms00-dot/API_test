import { useState } from 'react';

const API_URL = import.meta.env.VITE_API_URL || 'http://localhost:8080/bfhl';

const options = [
  { key: 'even_numbers', label: 'Even Numbers' },
  { key: 'odd_numbers', label: 'Odd Numbers' },
  { key: 'alphabets', label: 'Alphabets' },
  { key: 'special_characters', label: 'Special Characters' },
  { key: 'sum', label: 'Sum of Numbers' },
  { key: 'concat_string', label: 'Concat String' },
];

function App() {
  const [text, setText] = useState('{ "data": ["a", "1", "334", "4", "R", "$"] }');
  const [response, setResponse] = useState(null);
  const [error, setError] = useState('');
  const [selected, setSelected] = useState({
    even_numbers: true,
    odd_numbers: true,
    alphabets: true,
    special_characters: true,
    sum: true,
    concat_string: true,
  });
  const [showDropdown, setShowDropdown] = useState(false);

  const handleSubmit = async (event) => {
    event.preventDefault();
    setError('');
    setResponse(null);
    setShowDropdown(false);

    try {
      const payload = JSON.parse(text);
      const response = await fetch(API_URL, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(payload),
      });

      if (!response.ok) {
        setError(`Server error ${response.status}`);
        return;
      }

      const data = await response.json();
      setResponse(data);
      setShowDropdown(true);
    } catch (err) {
      setError('Invalid JSON payload. Please enter valid JSON.');
    }
  };

  const handleToggle = (key) => {
    setSelected((prev) => ({ ...prev, [key]: !prev[key] }));
  };

  return (
    <div className="page-container">
      <header>
        <h1>{response ? response.roll_number : 'BFHL API Portal'}</h1>
        <p>Enter a valid JSON payload and submit to call the BFHL backend.</p>
      </header>
      <main>
        <form onSubmit={handleSubmit}>
          <label htmlFor="jsonInput">JSON Request</label>
          <textarea
            id="jsonInput"
            value={text}
            onChange={(e) => setText(e.target.value)}
            placeholder='{ "data": ["a", "1", "334", "4", "R", "$"] }'
          />
          <button type="submit">Submit</button>
        </form>

        {error && <div className="error">{error}</div>}

        {showDropdown && response && (
          <div className="panel">
            <div className="multi-select">
              <p>Select response sections to render:</p>
              {options.map((option) => (
                <label key={option.key}>
                  <input
                    type="checkbox"
                    checked={selected[option.key]}
                    onChange={() => handleToggle(option.key)}
                  />
                  {option.label}
                </label>
              ))}
            </div>

            <div className="result">
              <h2>Rendered response</h2>
              
              {selected.even_numbers && response.even_numbers && (
                <div className="section">
                  <strong>Even Numbers:</strong>
                  <pre>{JSON.stringify(response.even_numbers || [], null, 2)}</pre>
                </div>
              )}

              {selected.odd_numbers && response.odd_numbers && (
                <div className="section">
                  <strong>Odd Numbers:</strong>
                  <pre>{JSON.stringify(response.odd_numbers || [], null, 2)}</pre>
                </div>
              )}

              {selected.alphabets && response.alphabets && (
                <div className="section">
                  <strong>Alphabets:</strong>
                  <pre>{JSON.stringify(response.alphabets || [], null, 2)}</pre>
                </div>
              )}

              {selected.special_characters && response.special_characters && (
                <div className="section">
                  <strong>Special Characters:</strong>
                  <pre>{JSON.stringify(response.special_characters || response.sepcial_characters || [], null, 2)}</pre>
                </div>
              )}

              {selected.sum && response.sum !== undefined && (
                <div className="section">
                  <strong>Sum:</strong>
                  <pre>{response.sum}</pre>
                </div>
              )}

              {selected.concat_string && response.concat_string !== undefined && (
                <div className="section">
                  <strong>Concat String:</strong>
                  <pre>{response.concat_string}</pre>
                </div>
              )}
            </div>

            <div className="response-preview">
              <h2>Full backend response</h2>
              <pre>{JSON.stringify(response, null, 2)}</pre>
            </div>
          </div>
        )}
      </main>
    </div>
  );
}

export default App;
