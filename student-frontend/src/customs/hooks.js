import { useContext } from "react";
import StateContext from "../contexts/StateContext";

export const useSearch = () => {
  const [listFound, setListFound] = useContext(StateContext);
  return [listFound, setListFound];
};
