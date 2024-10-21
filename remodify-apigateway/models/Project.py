from pydantic import BaseModel

class ProjectResource(BaseModel):
    name: str
    description: str
    businessId: int
    contractorId: int
    startDate: str
    finishDate: str
    image: str