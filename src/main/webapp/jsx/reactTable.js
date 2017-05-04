function TextInfo(name, numberOfLines, textLength) {
    this.name = name;
    this.numberOfLines = numberOfLines;
    this.textLength = textLength;
};

var tableData = new Array();

var App = React.createClass({
 
  loadTextInfoFromServer: function () {
    var self = this;
    $.ajax({
      url: "/filesManipulator/rest/textFilesController"
      }).then(function (data) {
          if (data !== null) {
              for(var el in data) {
                  tableData[el] = new TextInfo(data[el].name, data[el].numberOfLines, data[el].textLength);
              }
          }
      self.setState({tableData: tableData});
    });
  },
 
  getInitialState: function () {
    return {tableData: []};
  },
 
  componentDidMount: function () {
    this.loadTextInfoFromServer();
  },
 
  render() {
    return ( <TextInfoTable tableData={this.state.tableData}/> );
  }
});

var TextElem = React.createClass({
  render: function() {
    return (
      <tr>
        <td>{this.props.textInfo.name}</td>
        <td>{this.props.textInfo.numberOfLines}</td>
        <td>{this.props.textInfo.textLength}</td>
      </tr>);
  }
});

var TextInfoTable = React.createClass({
  render: function() {
    var rows = [];
    this.props.tableData.forEach(function(textInfo) {
      rows.push(<TextElem textInfo={textInfo} />);
    });
    return (
      <div className="container">
      <table className="table table-striped">
        <thead>
          <tr>
            <th>Name</th><th>Number of lines</th><th>Text length</th>
          </tr>
        </thead>
        <tbody>{rows}</tbody>
      </table>
      </div>);
  }
});
 
ReactDOM.render(<App />, document.getElementById('root') );