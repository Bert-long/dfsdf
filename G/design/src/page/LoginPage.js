import React, {useEffect, useState} from "react";
import "./css/LoginCss.css"
import {Input, Icon, Button} from "antd";
import 'antd/dist/antd.css';
import {withRouter} from "react-router-dom"

const Login = props => {
    const [userName, setUserName] = useState("");
    const [passWord, setPassWord] = useState("");
    const [apiName, setApiName] = useState("");
    const getApi = [];
    const loginClick = () => {
        const user = document.getElementById("user").value;
        const passWord = document.getElementById("passWord").value;
        const apiName = document.getElementById("apiSelect").value;
        console.log(apiName);
        if (user === "" && passWord !== "") {
            alert("请输入用户名")
        }
        if (passWord === "" && user !== "") {
            alert("请输入密码")
        }
        if (passWord !== "" && user !== "" && apiName !== "") {
            setUserName(user);
            setPassWord(passWord);
            setApiName(apiName);
        }
    };

    const defaultClick = () => {
        document.getElementById("user").value = "";
        document.getElementById("passWord").value = "";
    };
    useEffect(() => {
        if (userName !== "" && passWord !== "" && apiName !== "") {
            const message = {userName: userName, passWord: passWord, url: apiName};
            fetch("http://10.1.130.210:8082/db/login", {
                    method: "POST",
                    body: JSON.stringify(message),
                    headers: {
                        'Content-Type': 'application/json',
                    },
                }
            ).then((response) => {
                if (response.status === 200) {
                    props.history.push({pathname: "/DB", state: apiName});

                } else {
                    alert("登录失败");
                    document.getElementById("user").value = "";
                    document.getElementById("passWord").value = "";
                }
            })
        }

    }, [userName, passWord, apiName]);

    useEffect(() => {
        fetch("http://10.1.130.210:8082/searchOption/dbUrl", {
            method: "GET",
        }).then((response) => {
            return response.json().then((json) => {
                for (let key in json) {
                    getApi.push(json[key].url);
                }
                console.log(getApi);
                for (let i = 0; i < getApi.length; i++) {
                    let select = document.getElementById("apiSelect");
                    let option = document.createElement("option");
                    option.innerHTML = `${getApi[i]}`;
                    select.appendChild(option);
                }
            })
        });
    }, []);

    function inputOnBlur() {
        if (document.getElementById("user").value===""){
            document.getElementById("warnText").style.visibility = "visible";

        }
    }

    function inputOnFocus() {
        document.getElementById("warnText").style.visibility = "hidden";
    }

    function inputOnBlurPs() {
        if (document.getElementById("passWord").value===""){
            document.getElementById("warnTextPs").style.visibility = "visible";

        }
    }

    function inputOnFocusPs() {
        document.getElementById("warnTextPs").style.visibility = "hidden";
    }

    return (
        <div className="loginPage">
            <div className="loginHeader">
                数据库登录系统
            </div>
            <div className="loginBody">
                <div className="login">
                    <div className="">
                        用户名：<Input placeholder="请输入用户名" prefix={<Icon type="user" style={{color: 'rgba(0,0,0,.25)'}}/>}
                                   allowClear id="user" onBlur={inputOnBlur} onFocus={inputOnFocus}
                                   className="userName"/>
                        <span id="warnText">请输入用户名</span>
                    </div>
                    <div className="passWord">
                        密 &nbsp;&nbsp;码：
                        <Input.Password placeholder="请输入密码"
                                        prefix={<Icon type="lock" style={{color: 'rgba(0,0,0,.25)'}}/>}
                                        id="passWord" onFocus={inputOnFocusPs} onBlur={inputOnBlurPs}>

                        </Input.Password>
                        <span id="warnTextPs">请输入密码</span>
                    </div>
                    <div>
                        请输入服务器地址：
                        <div>
                            <select className="select" placeholder="请选择要进入的服务器地址" id="apiSelect">

                            </select>
                        </div>
                    </div>
                    <div>
                        <Button type="primary" className="defaultButton" onClick={defaultClick}>
                            取消
                        </Button>
                        <Button type="primary" className="loginButton" onClick={loginClick}>
                            登录
                        </Button>
                    </div>
                </div>
            </div>
        </div>
    )
};

export default withRouter(Login);