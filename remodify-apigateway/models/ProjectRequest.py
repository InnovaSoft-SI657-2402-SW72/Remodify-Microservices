from pydantic import BaseModel

class ProjectRequestResource(BaseModel):
    name: str
    surname: str
    email: str
    phone: str
    address: str
    city: str
    summary: str
    businessId: int
    contractorId: int
    deadlineDate: str
    rooms: int
    budget: int