import React, {Component} from 'react';


class Users extends Component {
    constructor(props) {
        super(props);
        this.state = {users: []};
    }

    componentDidMount() {
        fetch('http://localhost:7070/users', {headers: {"Authorization": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsInNjb3BlcyI6IlJPTEVfQURNSU4iLCJpYXQiOjE1NzU1NDc3NjksImV4cCI6MTU3NTU2NTc2OX0.Lai0VcEvJz3_V4IZFTOOtEQQpqfDS9ddEZwZUwONrDY"} })
            .then((response) => response.json())
            .then((responseData) => {
                this.setState({
                    users: responseData
                })
            })
            .catch(err => console.error(err))
    }

    render() {
        const tableRows = this.state.users.map((user, index) =>
            <tr key={index}>
                <td>{user.login}</td>
                <td>{user.accountDTO.name}</td>
                <td>{user.accountDTO.surname}</td>
            </tr>
        );
        return (
            <div className="App">
                <table>
                    <tbody>{tableRows}</tbody>
                </table>
            </div>
        );
    }

}

export default Users;