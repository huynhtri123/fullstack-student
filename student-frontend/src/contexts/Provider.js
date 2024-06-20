import { useState } from "react";
import StateContext from "./StateContext";

function Provider({ children }) {
  const [listFound, setListFound] = useState([]);
  return (
    <StateContext.Provider value={[listFound, setListFound]}>
      {children}
    </StateContext.Provider>
  );
}

export default Provider;
