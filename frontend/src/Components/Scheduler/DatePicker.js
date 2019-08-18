import React, { Component } from 'react'
import { Dropdown } from 'semantic-ui-react'
import './datepicker.css'


class DatePicker extends Component {
    onChangeMonth = (e, { value }) => {
        this.props.onMonthSelect(value);
    }

    onChangeYear = (e, { value }) => {
        this.props.onYearSelect(value);
    }

    render(){
        let { years, months } = this.props;
        return(
            <div>
                <Dropdown className = "year-picker"
                    placeholder="Select Year" 
                    onChange={this.onChangeYear}
                    options={years}
                    selection
                />
                <Dropdown className = "month-picker"
                    placeholder="Select Month" 
                    onChange={this.onChangeMonth} 
                    options={months} 
                    selection 
                />
            </div>
        )
    }
}

export default DatePicker