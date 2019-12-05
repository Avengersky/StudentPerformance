import React from 'react';
import './App.css';
import Users from "./components/Users";

function App() {
  return (
    <div className="App">
        <header className="Header">
          <h1 className="App-title">Users</h1>
        </header>
        <Users/>
    </div>
  );
}

export default App;
