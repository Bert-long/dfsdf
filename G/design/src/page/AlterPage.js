import React, {useEffect, useState} from "react";
import {Icon, Layout, Input, Button} from "antd";
import "./css/AlterCss.css"
const Alter = props => {
    const {Header, Content, Sider} = Layout;
    const [apiName, setApiName] = useState("");
    let api=[];
    let apiId=[];
    let [inputValue,setInputValue]=useState("");

    useEffect(() => {
        fetch("http://10.1.130.130:9001/dbManager/search", {
            method: "GET",
        }).then((response) => {
            return response.json().then((json) => {
           for (let key in json){
               api.push(json[key].url);
               apiId.push(json[key].id);
           }
           let ul=document.getElementById("apiList");
           for (let i=0;i<api.length;i++){
               let li=document.createElement("li");
               li.innerHTML=`${api[i]}`;
               li.setAttribute("id",`${apiId[i]}`);
               li.addEventListener("click",function () {
                  document.getElementById("addInput").value=li.innerText;
                  document.getElementById("alterInput").value=li.innerText;
                  setApiName(li.id);
                  setInputValue(li.innerText);
               });
               ul.appendChild(li);
               console.log(li.id)
           }
            })
        })
    },[]);

const addClick=()=>{
    let addText=document.getElementById("addInputUp").value;
    if (/.*[\u4e00-\u9fa5]+.*$/.test(addText)){
        alert("不能含有中文")
    }
    else {
        let message={url:addText};
        fetch("http://10.1.130.130:9001/dbManager/addUrl", {
            method:"POST",
            body:JSON.stringify(message),
            headers: {
                'Content-Type': 'application/json',
            },
        }).then((response) =>{
            console.log(response);
            if (response.status===200){
                alert("添加成功");
                window.location.replace("/Alter")
            }
            else {
                alert("添加失败");
            }
        })
    }

};
const deleteClick=()=>{
    let deleteText=document.getElementById("addInput").value;
    fetch(`http://10.1.130.130:9001/dbManager/deleteUrl/${apiName}`, {
        method:"get",

    }).then((response)=>{
          if (response.status===200){
              window.location.replace("/Alter");
          }else {
              alert("删除失败");
          }
    })
};
const updateClick=()=>{
    let updateText=document.getElementById("newInput").value;
    if (/.*[\u4e00-\u9fa5]+.*$/.test(updateText)){
        alert("不能含有中文")
    }
    else {
        let message={id:apiName,url:updateText};
        console.log(message);
        fetch("http://10.1.130.130:9001/dbManager/updateUrl", {
            method:"POST",
            body:JSON.stringify(message),
            headers: {
                'Content-Type': 'application/json',
            },
        }).then((response)=>{
            if (response.status===200){
                window.location.replace("/Alter")
            }
            else {
                alert("修改失败");
            }
        })
    }

};
const cancelClick=()=>{
    setInputValue("");
    setApiName("");
};
const testClick=()=>{
    let a=document.getElementById("1111").value;
    let message={name:a};
    fetch("", {
        method: "POST",
        body: JSON.stringify(message),
        headers: {
            'Content-Type': 'application/json',
        },
    }).then((response)=>{
        console.log(response.clone().json());
    })
};

    return (
        <div className="DBPage">
            <Layout>
                <Sider className="pageSider">
                    <span className="dbText"><Icon type="api" id="icon"></Icon>请选择服务器：</span><br/>
                    <ul className="dbList" id="apiList">
                        <li></li>
                    </ul>
                </Sider>
            </Layout>
            <Layout className="bodyLayout">
                <Header className="pageHeader">

                </Header>
                <Content className="pageContext">
                    <Input className="apiInput" id="addInputUp" ></Input><br/>

                    <Button type="primary" className="addButton" onClick={addClick}>
                        添加
                    </Button><br/>
                    <Input className="apiInput" id="addInput" value={inputValue}></Input><br/>

                    <Button type="primary" onClick={deleteClick} className="deleteButton">
                        删除
                    </Button>
                    <Button type="primary" className="addButton" onClick={cancelClick}>
                        取消
                    </Button>
                    <br/>
                   请添加需要修改的内容： <br/>
                  原内容：<br/> <Input className="apiInput" id="alterInput" value={inputValue}></Input><br/>
                   <br/>
                   修改内容：<br/> <Input className="apiInput" id="newInput" ></Input><br/>
                    <Button type="primary" className="alterButton" onClick={updateClick}>
                        修改
                    </Button>
                    <br/>
                    <input id="1111"/>
                    <Button onClick={testClick}>test</Button>
                </Content>
            </Layout>
        </div>
    )
};
export default Alter;