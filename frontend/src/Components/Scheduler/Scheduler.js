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
            year.push({key: 1+i, text: `${currentYear+i}`, value: currentYear+i})
        }
        this.setState({years: year});
    }

    //Returns a list of months that remains for the year
    getRemainingMonthsOfYear(year = moment().year()){
        let currentMonth = (year !== moment().year()) ? 0 : moment().month();
        let month = []
        while(currentMonth < 12){
            let monthName = moment().month(currentMonth).format("MMMM");
            month.push({key: currentMonth, text: `${monthName}`, value: currentMonth});
            currentMonth++;
        }
        this.setState({months: month})
    }

    //Returns a list of sundays
    getSundaysForSelectedMonthYear(selectedMonth, selectedYear){
        // Set chosenDate to the 1st of the selectedMonth in selectedYear.
        let chosenDate = moment().set({
            'year': this.state.selectedYear,
            'month': this.state.selectedMonth,
            'date': 1
        });

        let sundays = [];
        for(var i = 1; chosenDate.month() === this.state.selectedMonth; i++) {
            // If the month doesn't start on Monday, set chosenDate to the first Sunday of the month.
            if(chosenDate.day() !== 0) {
                chosenDate = chosenDate.day(7);
            }

            sundays.push({
                key: i,
                date: chosenDate.format("MMM D").toString()
            });

            chosenDate.add(7, 'd');
        }

        return sundays;
    }

    selectYear = (year, month) => {
        this.setState({selectedYear: year});
        this.getRemainingMonthsOfYear(year);
    }
    
    selectMonth = (month) => {
        this.setState({selectedMonth: month});
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