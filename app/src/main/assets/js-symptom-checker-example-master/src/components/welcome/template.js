/**
 * Created by Tomasz Gabrysiak @ Infermedica on 08/02/2017.
 */

const template = (context) => {
  return new Promise((resolve) => {
    resolve(`
      <h5 class="card-title">Welcome to the Symptom Checker.</h5>
        
        <p>Please click <span class="button1">Next</span> to move to the first question.</p>
        
      </div>
    `);
  });
};

export default template;
