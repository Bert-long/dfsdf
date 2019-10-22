import React, {useEffect, useState} from "react";
import "./css/DBOperation.css"
import {Layout, Icon, Input, Button} from "antd"
import "antd/dist/antd.css"
import {withRouter} from "react-router-dom"

const DBOperation = props => {
    const {Header, Content, Sider} = Layout;
    const {TextArea} = Input;
    const dbName = [];
    const tableName = [];
    const tableCopy = [];
    const [dbPostName, setDbPostName] = useState("");
    useEffect(() => {
        document.getElementById("tableNameList").innerHTML = "";
        fetch("http://10.1.130.210:8082/searchOption/searchDB", {
            method: "GET"
        }).then((response) => {
            return response.json().then((json) => {
                for (let key in json) {
                    dbName.push(json[key].databaseName);
                }
                console.log(dbName)
                for (let i = 0; i < dbName.length; i++) {
                    let copyLi = document.createElement("li");
                    copyLi.innerHTML = `${dbName[i]}`;
                    copyLi.setAttribute("id", `${dbName[i]}`);
                    copyLi.addEventListener("click", function () {
                        setDbPostName(`${dbName[i]}`);
                        fetch(`http://10.1.130.210:8082/searchOption/searchTable/${dbName[i]}`, {
                            method: "get"
                        }).then((response) => {
                            tableCopy.length = 0;
                            return response.json().then((json) => {
                                document.getElementById("displayText").style.display = "block";
                                document.getElementById("tableNameList").innerHTML = "";
                                let set = new Set();
                                for (let key in json) {
                                    set.add(json[key].tableDate);
                                }
                                for (let a of set) {
                                    tableName.push(a);
                                    tableCopy.push(a);
                                }
                                for (let i = 0; i < tableName.length; i++) {
                                    let tableUl = document.getElementById("tableNameList");
                                    let tableLi = document.createElement("li");
                                    tableLi.innerHTML = `${tableName[i]}`;
                                    tableLi.addEventListener("click", function () {
                                        let table = document.getElementById("table");
                                        let tBody = document.createElement("tbody");
                                        table.innerHTML = "";
                                        table.appendChild(tBody);
                                        let selectText = {condition: `select * from ${tableCopy[i]}`};
                                        console.log(selectText);
                                        fetch("http://10.1.130.210:8082/searchOption/searchTableData", {
                                            method: "POST",
                                            body: JSON.stringify(selectText),
                                            headers: {
                                                'Content-Type': 'application/json',
                                            },
                                        }).then((response) => {
                                            return response.json().then((json) => {
                                                let set = new Set();
                                                console.log(json);
                                                let selectValue = [];
                                                for (let key in json) {
                                                    set.add(json[key].tableColumn);
                                                    selectValue.push(json[key].tableRow);
                                                }
                                                let selectTable = [];
                                                for (let a of set) {
                                                    selectTable.push(a);
                                                }
                                                console.log(selectTable);
                                                let rowNumber = selectValue.length / selectTable.length;
                                                if (rowNumber < 2) {
                                                    let tr = document.createElement("tr");
                                                    let trBody = document.createElement("tr");
                                                    for (let i = 0; i < selectTable.length; i++) {
                                                        let th = document.createElement("th");
                                                        th.style.width = "30px";
                                                        th.innerHTML = `${selectTable[i]}`;
                                                        tr.appendChild(th);
                                                        let td = document.createElement("td");
                                                        if (selectValue[i] === "") {
                                                            td.innerHTML = "null";
                                                            trBody.appendChild(td);
                                                        } else {
                                                            td.innerHTML = `${selectValue[i]}`;
                                                            trBody.appendChild(td);
                                                        }
                                                    }
                                                    let table = document.getElementById("table");
                                                    table.appendChild(tr);
                                                    table.appendChild(trBody);
                                                } else {
                                                    let trHead = document.createElement("tr");
                                                    for (let a = 0; a < rowNumber; a++) {
                                                        let secondLine = document.createElement("tr");
                                                        for (let i = 0; i < selectTable.length; i++) {
                                                            let th = document.createElement("th");

                                                            th.innerHTML = `${selectTable[i]}`;
                                                            trHead.appendChild(th);

                                                            let table = document.getElementById("table");
                                                            let secondTd = document.createElement("td");
                                                            let num = a * selectTable.length;
                                                            if (selectValue[num + i] === "") {
                                                                secondTd.innerHTML = "null";
                                                                secondLine.appendChild(secondTd);
                                                                table.appendChild(secondLine);
                                                            } else {
                                                                secondTd.innerHTML = selectValue[num + i];
                                                                secondLine.appendChild(secondTd);
                                                                table.appendChild(secondLine);
                                                            }
                                                        }
                                                    }
                                                    let tr = document.createElement("tr");
                                                    tr.style.backgroundColor = "#d9d9d9";
                                                    for (let i = 0; i < selectTable.length; i++) {
                                                        let th = document.createElement("th");
                                                        th.innerHTML = `${selectTable[i]}`;
                                                        tr.appendChild(th);
                                                    }
                                                    let table = document.getElementById("table");
                                                    table.replaceChild(tr, table.childNodes[0]);
                                                    console.log(tr)
                                                }

                                            })
                                        })
                                    });
                                    tableUl.appendChild(tableLi);
                                }
                                tableName.length = 0;
                            })
                        })
                    });
                    document.getElementById("list").appendChild(copyLi);
                }
            })
        })
    }, []);


    const outClick = () => {
        fetch("http://10.1.130.210:8082/db/logout", {
            method: "get"
        }).then((response) => {
            if (response.status === 200) {
                window.location.replace("/");
            } else {
                alert("退出失败");
            }
        });
    };
    const submitClick = () => {
        let table = document.getElementById("table");
        let tBody = document.createElement("tbody");
        table.innerHTML = "";
        table.appendChild(tBody);
        const sql = document.getElementById("textArea").value.toLowerCase();
        const firstLetter = sql.substr(0, 1);
        const secondLetter = sql.substr(1, 1);
        if (firstLetter === "s" || secondLetter === "s") {
            let selectText = {condition: sql};
            fetch("http://10.1.130.210:8082/searchOption/searchTableData", {
                method: "POST",
                body: JSON.stringify(selectText),
                headers: {
                    'Content-Type': 'application/json',
                },
            }).then((response) => {
                if (response.status !== 200) {
                    alert("查询失败")
                } else {
                    return response.json().then((json) => {
                        console.log(json);
                        let set = new Set();
                        let selectValue = [];
                        for (let key in json) {
                            set.add(json[key].tableColumn);
                            selectValue.push(json[key].tableRow);
                        }
                        let selectTable = [];
                        for (let a of set) {
                            selectTable.push(a);
                        }
                        let rowNumber = selectValue.length / selectTable.length;
                        if (rowNumber < 2) {
                            let tr = document.createElement("tr");
                            let trBody = document.createElement("tr");
                            for (let i = 0; i < selectTable.length; i++) {
                                let th = document.createElement("th");
                                th.style.width = "30px";
                                th.innerHTML = `${selectTable[i]}`;
                                tr.appendChild(th);
                                let td = document.createElement("td");
                                if (selectValue[i] === "") {
                                    td.innerHTML = "null";
                                    trBody.appendChild(td);
                                } else {
                                    td.innerHTML = `${selectValue[i]}`;
                                    trBody.appendChild(td);
                                }
                            }
                            let table = document.getElementById("table");
                            table.appendChild(tr);
                            table.appendChild(trBody);
                        } else {
                            let trHead = document.createElement("tr");
                            for (let a = 0; a < rowNumber; a++) {
                                let secondLine = document.createElement("tr");
                                for (let i = 0; i < selectTable.length; i++) {
                                    let th = document.createElement("th");

                                    th.innerHTML = `${selectTable[i]}`;
                                    trHead.appendChild(th);

                                    let table = document.getElementById("table");
                                    let secondTd = document.createElement("td");
                                    let num = a * selectTable.length;
                                    if (selectValue[num + i] === "") {
                                        secondTd.innerHTML = "null";
                                        secondLine.appendChild(secondTd);
                                        table.appendChild(secondLine);
                                    } else {
                                        secondTd.innerHTML = selectValue[num + i];
                                        secondLine.appendChild(secondTd);
                                        table.appendChild(secondLine);
                                    }
                                }
                            }
                            let tr = document.createElement("tr");
                            tr.style.backgroundColor = "#d9d9d9";
                            for (let i = 0; i < selectTable.length; i++) {
                                let th = document.createElement("th");
                                th.innerHTML = `${selectTable[i]}`;
                                tr.appendChild(th);
                            }
                            let table = document.getElementById("table");
                            table.replaceChild(tr, table.childNodes[0]);
                            console.log(tr)

                        }


                    })
                }
            })
        } else {
            if (dbPostName!=="mysql" && dbPostName!=="information_schema"&&dbPostName!=="performance_schema"&&dbPostName!=="sys"){
                let elseText = {condition: sql};
                console.log(elseText);
                fetch("http://10.1.130.210:8082/optionData", {
                    method: "POST",
                    body: JSON.stringify(elseText),
                    headers: {
                        'Content-Type': 'application/json',
                    },
                }).then((response) => {
                    if (response.status === 200) {
                        window.location.replace("/DB")
                    } else {
                        alert("操作失败");
                    }
                })
            }
            else {
                alert("该数据库只能查找");
            }

        }

    };
    return (
        <div className="DBPage">
            <Layout>
                <Sider className="pageSider">
                    <span className="dbText"><Icon type="database" id="icon"></Icon>请选择数据库：</span><br/>

                    <ul className="dbList" id="list">
                    </ul>
                    <span className="dbText" id="displayText"><Icon type="table"
                                                                    id="icon"></Icon>{dbPostName}中的表如下：</span><br/>
                    <ul className="tableList" id="tableNameList">
                    </ul>
                </Sider>

            </Layout>
            <Layout className="bodyLayout">
                <Header className="pageHeader">
                    <div className="WelcomeText">
                        欢迎进入数据库
                    </div>
                </Header>
                <Content className="pageContext">
                    <TextArea className="inputText" placeholder="请输入要执行的语句" id="textArea">

                    </TextArea><br/>
                    <Button type="primary" className="deleteButton" onClick={outClick}>
                        退出
                    </Button>
                    <Button type="primary" className="submitButton" onClick={submitClick}>
                        确认
                    </Button>
                    <div className="tableDiv">
                        <table className="dbTable" id="table">
                            <tbody>

                            </tbody>
                        </table>
                    </div>
                </Content>
            </Layout>
        </div>
    )
};
export default withRouter(DBOperation);