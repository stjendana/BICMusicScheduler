import React, { Component } from 'react'
import DatePicker from './DatePicker'
import Table from './Table'
import { Button } from 'semantic-ui-react'
import './scheduler.css'

const moment = require("moment")

const SendButton = () => {
    return (
        <Button positive floated='right' className = "send-button"> Send To All </Button>
    )
}

class Scheduler extends Component {
    state = {
        listOfRoles: ['WL', 'Singer', 'Keyboard', 'Filler', 'MultiMedia', 'Soundman'],
        listOfPeople: [
            {key: "1", name : "kevin ismantara", role: "soundman"},
            {key: "2", name : "steven tjendana", role: "singer"},
            {key: "3", name : "yonathan", role: "wl"}
        ],
        years: [],
        months: [],
        selectedYear: null,
        selectedMonth: null
    }

    UNSAFE_componentWillMount(){
        this.getYearsAhead();
        this.getRemainingMonthsOfYear();
    }

     //Returns a list of years: 5 ahead
     getYearsAhead(){
        let currentDate = new Date();
        let currentYear = currentDate.getFullYear();
        let year = [];
        for(let i=0; i<5; i++){
            year.push({key: 1+i, text: `${currentYear+i}`, value: `${currentYear+i}`})
        }
        this.setState({years: year});
    }

    //Returns a list of months that remains for the year
    getRemainingMonthsOfYear(){
        let currentDate = new Date();
        let currentMonth = currentDate.getMonth();
        let month = []
        while(currentMonth < 12){
            let monthName = moment().month(currentMonth).format("MMMM");
            month.push({key: currentMonth, text: `${monthName}`, value: `${monthName}`});
            currentMonth++;
        }
        this.setState({months:month})
    }

    //Returns a list of sundays
    getSundaysForSelectedMonthYear(selectedMonth, selectedYear){
        let sundaysOfMonth = [];
        let sunday = moment().startOf('month').day("Sunday");
        if (sunday.date() > 7) sunday.add(7,'d');
        let month = sunday.month();
        let index = 1
        while(month === sunday.month()){
            sundaysOfMonth.push({key: index, date: sunday.format("MMM D").toString()});           
            sunday.add(7,'d');
            index++;
        }
        return sundaysOfMonth;
    }

    selectYear = (year, month) => {
        this.setState({selectedYear: year});
    }
    
    selectMonth = (month) => {
        this.setState({selectedMonth: month})
    }

    /*
    onClick = () => {
        //Pass to backend
    }
    */

    render(){
        let { selectedYear, selectedMonth } = this.state;
        let listOfSundays;
        if(selectedYear && selectedMonth){
            listOfSundays = this.getSundaysForSelectedMonthYear(selectedYear, selectedMonth);
        }
        return(
            <div>
                <DatePicker years = {this.state.years} months = {this.state.months} onYearSelect={this.selectYear} onMonthSelect={this.selectMonth}/>
                <Table listOfSundays = {listOfSundays} listOfPeople = {this.state.listOfPeople} listOfRoles = {this.state.listOfRoles}/>
                {listOfSundays && <SendButton/>}
            </div>
        )
    }
}

export default Scheduler