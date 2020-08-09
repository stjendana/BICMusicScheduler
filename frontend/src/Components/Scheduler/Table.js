import React, { Component } from 'react'
import { Table as Formatter, Dropdown } from 'semantic-ui-react'
import './table.css'

const friendOptions = [
    {
      key: 'Kevin Ismantara',
      text: 'Kevin Ismantara',
      value: 'Kevin Ismantara',
    },
    {
      key: 'Steven Tjendana',
      text: 'Steven Tjendana 123',
      value: 'Steven Tjendana',
    },
    {
      key: 'Yonathan',
      text: 'Yonathan',
      value: 'Yonathan',
    }
]

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

const WeekRender = (props) => {
    let { listOfSundays, listOfRoles, listOfPeople } = props;
    let options = listOfPeople.map(o => { return { key: o.key, text: o.name, value: o.key}});
    let arrayOfDropdown = [];
    for(let i=0; i < listOfSundays.length; i++) {
        arrayOfDropdown.push(
            <Formatter.Cell>
                <Dropdown
                    placeholder='Select Person'
                    fluid
                    selection
                    options={options}
                />
            </Formatter.Cell>
        );
    }

    return (
        <Formatter.Body>
            {listOfRoles.map((roles) => {
                return(
                    <Formatter.Row>
                        <Formatter.Cell textAlign='center'> {roles} </Formatter.Cell>
                        {arrayOfDropdown}
                    </Formatter.Row>
                )
            })}
        </Formatter.Body>
    )
}


class Table extends Component {
    state = {
        selectedWL: null,
        selectedSoundMan: null,
        selectedSinger: null,
        selectedKeyboardist: null,
        selectedFiller: null,
        selectedMultiMedia: null
    }

    render(){
        var { listOfRoles, listOfSundays, listOfPeople } = this.props
        if(!listOfSundays){
            return null;
        } else {
            return (
                <div>
                    <Formatter celled singleLine className = "schedule-table">
                        <DynamicTableHeader listOfSundays = {listOfSundays} />
                        <WeekRender
                            listOfSundays = {listOfSundays}
                            listOfRoles = {listOfRoles}
                            listOfPeople = {listOfPeople}
                        />
                    </Formatter>
                </div>
            )
        }
    }
}

export default Table