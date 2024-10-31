from pydantic import BaseModel

class UsersResource(BaseModel):
    email: str
    description: str
    phone: str
    image: str
