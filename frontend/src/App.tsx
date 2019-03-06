import React, { Component } from 'react';
import './App.scss';
interface State {}
interface Props {}

class App extends Component<Props, State> {
  render() {
    return (
      <div className="App">
        <div className="login box">
          <div className="field">
            <label className="label">Логин</label>
            <div className="control">
              <input className="input" type="text"/>
            </div>
          </div>
          <div className="field">
            <label className="label">Пароль</label>
            <div className="control">
              <input className="input" type="password"/>
            </div>
          </div>
          <div className="field">
            <a className="button is-medium is-fullwidth">Войти</a>
          </div>
        </div>


      </div>
    );
  }
}

export default App;
