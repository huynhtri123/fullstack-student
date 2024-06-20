import { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import studentApi from "../../api/studentApi";

function ViewStudent() {
  const [student, setStudent] = useState({
    name: "",
    email: "",
    address: "",
  });

  const { id } = useParams();

  useEffect(() => {
    getStudent(id);
  }, []);

  const getStudent = async (id) => {
    const result = await studentApi.get(id);
    setStudent(result.data);
  };

  return (
    <div className="container text-center">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">View Student Details</h2>
          <div className="card">
            <div className="card-header">
              Details of student with id: {student.id}
              <ul className="list-group list-group-flush">
                <li className="list-group-item">
                  <b>Name: </b> {student.name}
                </li>
                <li className="list-group-item">
                  <b>Email: </b> {student.email}
                </li>
                <li className="list-group-item">
                  <b>Address: </b> {student.address}
                </li>
              </ul>
            </div>
          </div>
          <Link className="btn btn-primary my-2" to={"/"}>
            Back to home
          </Link>
        </div>
      </div>
    </div>
  );
}

export default ViewStudent;
