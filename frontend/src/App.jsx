import { useState } from 'react';

const API_URL = import.meta.env.VITE_API_URL || 'http://localhost:8080/bfhl';

const options = [
  { key: 'alphabets', label: 'Alphabets' },
  { key: 'numbers', label: 'Numbers' },
  { key: 'highest_lowercase_alphabet', label: 'Highest lowercase alphabet' },
];

function App() {
  const [text, setText] = useState('{ "data": ["A", "C", "z"] }');
  const [response, setResponse] = useState(null);
  const [error, setError] = useState('');
  const [selected, setSelected] = useState({
    alphabets: true,
    numbers: true,
    highest_lowercase_alphabet: true,
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
        <h1>0827CI243D06</h1>
        <p>Enter a valid JSON payload and submit to call the BFHL backend.</p>
      </header>
      <main>
        <form onSubmit={handleSubmit}>
          <label htmlFor="jsonInput">JSON Request</label>
          <textarea
            id="jsonInput"
            value={text}
            onChange={(e) => setText(e.target.value)}
            placeholder='{ "data": ["A", "C", "z"] }'
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
              {selected.alphabets && (
                <div className="section">
                  <strong>Alphabets:</strong>
                  <pre>{JSON.stringify(response.alphabets || [], null, 2)}</pre>
                </div>
              )}
              {selected.numbers && (
                <div className="section">
                  <strong>Numbers:</strong>
                  <pre>{JSON.stringify(response.numbers || [], null, 2)}</pre>
                </div>
              )}
              {selected.highest_lowercase_alphabet && (
                <div className="section">
                  <strong>Highest lowercase alphabet:</strong>
                  <pre>{JSON.stringify(response.highest_lowercase_alphabet || [], null, 2)}</pre>
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
