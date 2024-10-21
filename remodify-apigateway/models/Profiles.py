from pydantic import BaseModel

class ProfilesResource(BaseModel):
    email: str
    password: str
    typeUser: str
    firstName: str
    paternalSurname: str
    maternalSurname: str