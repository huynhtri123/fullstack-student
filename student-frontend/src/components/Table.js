import { useEffect, useRef, useState } from "react";
import { Link } from "react-router-dom";
import { toast } from "react-toastify";

import studentApi from "../api/studentApi";
import { useSearch } from "../customs/hooks";

function Table() {
  const [listStudents, setListStudents] = useState([]);

  //use list found from search (useContext)
  const [listFound, setListFound] = useSearch();

  useEffect(() => {
    getListStudents();
  }, []);

  const getListStudents = async () => {
    const list = await studentApi.getAll();
    setListStudents(list.data);
  };

  const handleDeleteStudent = async (id) => {
    await studentApi.delete(id);
    toast.warning(`Deleted student with id = ${id}`);
    getListStudents();
  };
  
  const studentsToDisplay = listFound.length > 0 ? listFound : listStudents;

  return (
    <>
      <table className="table text-center">
        <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">Name</th>
            <th scope="col">Email</th>
            <th scope="col">Adress</th>
            <th scope="col">Action</th>
          </tr>
        </thead>
        <tbody>
          {studentsToDisplay.map((student, index) => (
            <tr key={student.id}>
              <th scope="row">{index + 1}</th>
              <td>{student.name}</td>
              <td>{student.email}</td>
              <td>{student.address}</td>
              <td>
                <Link
                  to={`/viewStudent/${student.id}`}
                  className="btn btn-primary mx-2"
                >
                  View
                </Link>
                <Link
                  to={`/updateStudent/${student.id}`}
                  className="btn btn-outline-primary mx-2"
                >
                  Update
                </Link>
                <button
                  className="btn btn-danger mx-2"
                  onClick={() => handleDeleteStudent(student.id)}
                >
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </>
  );
}

export default Table;
