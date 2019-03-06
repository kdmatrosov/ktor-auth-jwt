import React, {Component} from 'react';
import axios from 'axios';
import './App.scss';

interface State {
  login: string,
  password: string,
}

class App extends Component {
  state: State = {
    login: '',
    password: '',
  };

  onValueChange = (key: string, value: string) => this.setState({[key]: value});

  startAuth = async () => {
    const {login, password} = this.state;
    try {
      const {data: token} = await axios.post('api/auth', {login, password});
      console.log(token);
    } catch (e) {
      console.error(e);
    }
  };

  render() {
    const {login, password} = this.state;
    return (
      <div className="App">
        <div className="login box">
          <div className="field">
            <label className="label">Логин</label>
            <div className="control">
              <input className="input" type="text" value={login} onChange={({target: {value}}) => {
                this.onValueChange('login', value);
              }}/>
            </div>
          </div>
          <div className="field">
            <label className="label">Пароль</label>
            <div className="control">
              <input className="input" type="password" value={password} onChange={({target: {value}}) => {
                this.onValueChange('password', value);
              }}/>
            </div>
          </div>
          <div className="field">
            <a className="button is-medium is-fullwidth" onClick={this.startAuth}>Войти</a>
          </div>
        </div>


      </div>
    );
  }
}

export default App;
