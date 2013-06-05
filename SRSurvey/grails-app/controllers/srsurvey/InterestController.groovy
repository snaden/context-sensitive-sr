package srsurvey

class InterestController {
    def create() {
        String email = params.emails
        print(email)
        Person p = Person.findByEmail(email)
        print(p)

        //put the person into session
        if(session.person==null){
            session.person = p.id
        }

        render(view:'create')
    }

    def process() {
        List<String> inputs = params.get("interest_inputs")

        //Find the person
        Person p = Person.findById(session.person)

        //Associate the person with the interest
        PersonService ps = new PersonService(p)
        for (interest in inputs){
            ps.addInterest(interest)
        }

        //Assign Group
        SRService sr = new SRService()
        sr.assignGroup(p, inputs)

    }

    def index() {


    }
}