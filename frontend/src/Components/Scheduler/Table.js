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
      text: 'Steven Tjendana',
      value: 'Steven Tjendana',
    },
    {
      key: 'Yonathan',
      text: 'Yonathan',
      value: 'Yonathan',
    }
]

const StaticTableHeader = () => {
    return (
        <Formatter.Header>
            <Formatter.Row>
                <Formatter.HeaderCell/>
                <Formatter.HeaderCell textAlign='center'> Week 1 </Formatter.HeaderCell>
                <Formatter.HeaderCell textAlign='center'> Week 2 </Formatter.HeaderCell>
                <Formatter.HeaderCell textAlign='center'> Week 3 </Formatter.HeaderCell>
                <Formatter.HeaderCell textAlign='center'> Week 4 </Formatter.HeaderCell>
            </Formatter.Row>
        </Formatter.Header>
        
    )
}

const DynamicTableHeader = (props) => {
    let { listOfSundays } = props
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

const FourWeekRender = (props) => {
    let { listOfRoles } = props;
    return (
        <Formatter.Body>
            {listOfRoles.map((roles) => {
                return(
                    <Formatter.Row>
                        <Formatter.Cell textAlign='center'> {roles} </Formatter.Cell>
                        <Formatter.Cell>
                            <Dropdown
                                placeholder='Select Person'
                                fluid
                                selection
                                options={friendOptions}
                            />
                        </Formatter.Cell>
                        <Formatter.Cell>
                            <Dropdown
                                placeholder='Select Person'
                                fluid
                                selection
                                options={friendOptions}
                            />
                        </Formatter.Cell>
                        <Formatter.Cell>
                            <Dropdown
                                placeholder='Select Person'
                                fluid
                                selection
                                options={friendOptions}
                            />
                        </Formatter.Cell>
                        <Formatter.Cell>
                            <Dropdown
                                placeholder='Select Person'
                                fluid
                                selection
                                options={friendOptions}
                            />
                        </Formatter.Cell>
                    </Formatter.Row>
                )
            })}
        </Formatter.Body>
    )
}

const FiveWeekRender = (props) => {
    let { listOfRoles } = props;
    return (
        <Formatter.Body>
            {listOfRoles.map((roles) => {
                return(
                    <Formatter.Row>
                        <Formatter.Cell textAlign='center'> {roles} </Formatter.Cell>
                        <Formatter.Cell>
                            <Dropdown
                                placeholder='Select Person'
                                fluid
                                selection
                                options={friendOptions}
                            />
                        </Formatter.Cell>
                        <Formatter.Cell>
                            <Dropdown
                                placeholder='Select Person'
                                fluid
                                selection
                                options={friendOptions}
                            />
                        </Formatter.Cell>
                        <Formatter.Cell>
                            <Dropdown
                                placeholder='Select Person'
                                fluid
                                selection
                                options={friendOptions}
                            />
                        </Formatter.Cell>
                        <Formatter.Cell>
                            <Dropdown
                                placeholder='Select Person'
                                fluid
                                selection
                                options={friendOptions}
                            />
                        </Formatter.Cell>
                        <Formatter.Cell>
                            <Dropdown
                                placeholder='Select Person'
                                fluid
                                selection
                                options={friendOptions}
                            />
                        </Formatter.Cell>
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
        var { listOfRoles, listOfSundays } = this.props
        if(!listOfSundays){
            return null;
        }
        if(listOfSundays && listOfSundays.length === 4){
            return (
                <div>
                    <Formatter celled singleLine className = "schedule-table">
                        <DynamicTableHeader listOfSundays = {listOfSundays} listOfRoles = {listOfRoles} />
                        <FourWeekRender listOfRoles = {listOfRoles} />
                    </Formatter>
                </div>
            )
        }
        if(listOfSundays && listOfSundays.length === 5){
            return (
                    <div>
                        <Formatter celled singleLine className = "schedule-table">
                            <DynamicTableHeader listOfRoles = {listOfRoles} />
                            <FiveWeekRender listOfRoles = {listOfRoles} />
                        </Formatter>
                    </div>
            )
        }
    }
}

export default Table