import React from "react";
import {BrowserRouter, Switch, Route} from "react-router-dom";
import Login from "./page/LoginPage";
import DBOperation from "./page/DBOperationPage";
import Alter from "./page/AlterPage";
const Router = () => (
    <BrowserRouter>
        <Switch>
            <Route exact={true} path="/" component={Login}/>
            <Route exact={true} path="/DB" component={DBOperation}/>
            <Route exact={true} path="/alter" component={Alter}/>

        </Switch>
    </BrowserRouter>
);
export default Router;