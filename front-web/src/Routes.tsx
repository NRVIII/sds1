import React from 'react';
import { BrowserRouter, Switch, Route } from "react-router-dom";
import Header from './components/Header';
import Home from './pages/Home';
import Records from './pages/Records';
import Charts from './pages/Charts';

const Routes = () => (
  <BrowserRouter>
    <Header />

    <Switch>
      {/* HOME PATH */}
      <Route path="/" exact>
        <Home />
      </Route>

      {/* RECORDS */}
      <Route path="/records">
        <Records />
      </Route>

      {/* CHARTS */}
      <Route path="/charts">
        <Charts />
      </Route>

    </Switch>

  </BrowserRouter>
);

export default Routes;