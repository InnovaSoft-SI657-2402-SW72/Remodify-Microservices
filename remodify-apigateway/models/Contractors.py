from pydantic import BaseModel

class ContractorsResource(BaseModel):
    description: str
    phone: str