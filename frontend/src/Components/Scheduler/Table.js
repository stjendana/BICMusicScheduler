import React, { Component } from 'react'
import { Table as Formatter, Dropdown } from 'semantic-ui-react'
import './table.css'

const DynamicTableHeader = (props) => {
    let { listOfSundays } = props;
    return (
        <Formatter.Header>
            <Formatter.Row>
                <Formatter.HeaderCell/>
                {
                    listOfSundays.map((sunday) => {
                        return (
                            <Formatter.HeaderCell textAlign='center'> {sunday.date} </Formatter.HeaderCell>
                        )
                    })
                }
            </Formatter.Row>
        </Formatter.Header>
    )
    
}

const RowRender = (props) => {
    let { listOfSundays, listOfRoles, listOfPeople, handleChange, rowIdx } = props;
    let options = listOfPeople.map(o => { return { key: o.key, text: o.name, value: o.key}});
    const onChange = (e, data, columnIdx) => {
        handleChange(data.value, columnIdx, rowIdx);
    };
    return (listOfSundays.map((val, idx) => {
        return (
            <Formatter.Cell>
                <Dropdown
                    placeholder='Select Person'
                    fluid
                    selection
                    options={options}
                    onChange={(e, data) => onChange(e, data, idx) }
                />
            </Formatter.Cell>
        )
    }))}

const WeekRender = (props) => {
    let { listOfSundays, listOfRoles, listOfPeople, handleChange } = props;
    return (
        <Formatter.Body>
            {listOfRoles.map((roles, idx) => {
                return(
                    <Formatter.Row>
                        <Formatter.Cell textAlign='center'> {roles.name} </Formatter.Cell>
                        <RowRender listOfSundays = {listOfSundays}
                            listOfRoles = {listOfRoles}
                            listOfPeople = {listOfPeople}
                            handleChange={handleChange}
                            rowIdx={idx}
                        ></RowRender>
                    </Formatter.Row>
                )
            })}
        </Formatter.Body>
    )
}


class Table extends Component {
    constructor(props) {
        super(props)
        let schedulesPopulated = false;
    }
    state = {
        schedules: []
    }

    AutoPopulateSchedules = () => {
        console.log('autopopulating...');

        let schedulesForThisMonth = [];
        this.props.listOfSundays.forEach(dt => {
            this.props.listOfRoles.forEach(role => {
                schedulesForThisMonth.push({
                    userId: -1,
                    ministryId: role.id,
                    date: dt.timestamp * 1000
                });
            });
        });
        this.setState({schedules: schedulesForThisMonth})
    }

    handleChange = (userId, columnIdx, rowIdx) => {
        this.setState({schedules: this.RemoveDuplicate(this.state.schedules, { userId: userId, ministryId: this.props.listOfRoles[rowIdx].id, date: this.props.listOfSundays[columnIdx].timestamp * 1000})})
    }

    RemoveDuplicate = (current, newObj) => {
        const existing = current.find(o => o.ministryId === newObj.ministryId && o.date === newObj.date);
        if (existing) {
            existing.userId = newObj.userId;
            return current;
        }
        return [...current, newObj];
    }

    save = () => {
        console.log(this.state.schedules);

        fetch('http://localhost:8080/saveSchedules', {
                method: 'POST',
                body: JSON.stringify(this.state.schedules),
                withCredentials: true,
                headers: new Headers({
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + localStorage.getItem('m3-auth-token'),
                    'Access-Control-Allow-Origin': '*',
                })                 
            }).then()
    }

    render(){
        var { listOfRoles, listOfSundays, listOfPeople } = this.props
        if(!listOfSundays){
            return null;
        } else {        
            if (!this.schedulesPopulated) {
                this.AutoPopulateSchedules();
                this.schedulesPopulated = true;
            }
            return (
                <div>
                    <Formatter celled singleLine className = "schedule-table">
                        <DynamicTableHeader listOfSundays = {listOfSundays} />
                        <WeekRender
                            listOfSundays = {listOfSundays}
                            listOfRoles = {listOfRoles}
                            listOfPeople = {listOfPeople}
                            handleChange={this.handleChange}
                        />
                    </Formatter>
                </div>
            )
        }
    }
}

export default Table