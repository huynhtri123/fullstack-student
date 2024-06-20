import {
  Link,
  Routes,
  Route,
  useLocation,
  useNavigate,
} from "react-router-dom";
import { useState } from "react";

import {
  HomePage,
  AddStudent,
  UpdateStudent,
  ViewStudent,
} from "../components";
import "../scss/Navbar.scss";
import studentApi from "../api/studentApi";
import { useSearch } from "../customs/hooks";

function Navbar() {
  const location = useLocation();
  // console.log(location.pathname);

  //search by name
  const [inputSearch, setInputSearch] = useState("");
  const [listFound, setListFound] = useSearch();

  const handleChangeInputSearch = (e) => {
    setInputSearch(e.target.value);
  };

  const navigate = useNavigate();

  const handleSubmitSearch = (e) => {
    e.preventDefault();
    searchByName();
    setInputSearch("");
    navigate("/");
  };

  const searchByName = async () => {
    const listStudentFound = await studentApi.searchByName(inputSearch);
    setListFound(listStudentFound.data);
  };

  //clear
  const clearSearchResults = () => {
    setListFound([]);
  };

  return (
    <div className="nav-bar">
      <nav className="navbar navbar-expand-lg custom-navbar-bg bg-dark bg-opacity-10">
        <div className="container-fluid">
          <Link
            className={`navbar-brand nav-link-hover ${
              location.pathname === "/" ? "active" : ""
            }`}
            to="/"
            onClick={clearSearchResults}
          >
            Home
          </Link>
          <Link
            className={`navbar-brand nav-link-hover ${
              location.pathname === "/addStudent" ? "active" : ""
            }`}
            to="/addStudent"
          >
            Add Student
          </Link>

          <button
            className="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span className="navbar-toggler-icon"></span>
          </button>
          <div className="collapse navbar-collapse" id="navbarSupportedContent">
            <form
              className="d-flex"
              role="search"
              onSubmit={(e) => handleSubmitSearch(e)}
            >
              <input
                className="form-control me-2"
                type="search"
                placeholder="Search"
                aria-label="Search"
                value={inputSearch}
                onChange={(e) => handleChangeInputSearch(e)}
              />
              <button className="btn btn-outline-primary" type="submit">
                Search
              </button>
            </form>
          </div>
        </div>
      </nav>

      <Routes>
        <Route path="/" element={<HomePage />}></Route>
        <Route path="/addStudent" element={<AddStudent></AddStudent>}></Route>
        <Route
          path="/updateStudent/:id"
          element={<UpdateStudent></UpdateStudent>}
        ></Route>
        <Route
          path="/viewStudent/:id"
          element={<ViewStudent></ViewStudent>}
        ></Route>
      </Routes>
    </div>
  );
}

export default Navbar;
