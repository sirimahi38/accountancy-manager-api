import logo from './logo.svg';
import './App.css';
import ListTasksComponent from './components/ListTasksComponent'
// import Home from './components/Home';
function App() {
  return (
    <div>

        <p>
          <h1>Accountancy Manager</h1>
          <ListTasksComponent/>
        </p>
          {/*<Home />*/}
    </div>
  );
}

export default App;
