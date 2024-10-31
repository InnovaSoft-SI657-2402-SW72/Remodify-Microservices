from pydantic import BaseModel

class SignUpRequest(BaseModel): 
    username: str
    password: str
    roles: list = [
        "ROLE_REMODELER",
        "ROLE_CONTRACTOR"
    ]
    email: str
    firstName: str
    paternalSurname: str
    maternalSurname: str
    description: str
    phone: str
    image: str
