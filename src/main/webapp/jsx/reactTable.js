function TextInfo(name, numberOfLines, textLength) {
    this.name = name;
    this.numberOfLines = numberOfLines;
    this.textLength = textLength;
};

var tableData = new Array();

var Table = Reactable.Table,
    Thead = Reactable.Thead,
    Th = Reactable.Th;

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

var TextInfoTable = React.createClass({
  render: function() {
    
    return (
      <div className="container">
      <Table className="table table-striped fixed"
            filterable={['name', 'numberOfLines', 'textLength']}
            noDataText="No matching records found"
            itemsPerPage={10}
            currentPage={0}
            sortable={true}
            data={tableData}>
        <Thead>
            <Th column="name">Name</Th>
            <Th column="numberOfLines">Number of lines</Th>
            <Th column="textLength">Text length</Th>
        </Thead>
      </Table>
      </div>);
  }
});
 
ReactDOM.render(<App />, document.getElementById('root') );